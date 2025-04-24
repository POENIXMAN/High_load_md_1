package ru.hpclab.hl.module1.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ObservabilityService {
    private final ConcurrentHashMap<String, TimingStats> timings = new ConcurrentHashMap<>();
    private static final long MINUTE_IN_MILLIS = 60_000;
    private static final long THIRTY_SECONDS_IN_MILLIS = 30_000;
    private static final long TEN_SECONDS_IN_MILLIS = 10_000;

    private static final long MAX_RETENTION_TIME = 2 * MINUTE_IN_MILLIS;

    public void start(String name) {
        timings.computeIfAbsent(name, k -> new TimingStats()).start();
    }

    public void stop(String name) {
        TimingStats stats = timings.get(name);
        if (stats != null) {
            stats.stop();
        }
    }

    @Scheduled(fixedRate = 30_000)
    public void printStats() {
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String reportTime = sdf.format(new Date(now));

        System.out.println("=== Statistics Report (" + reportTime + ") ===");

        timings.forEach((name, stats) -> {
            System.out.println("Metric: " + name);
            System.out.println("  Last 10s: " + stats.getStats(now, TEN_SECONDS_IN_MILLIS));
            System.out.println("  Last 30s: " + stats.getStats(now, THIRTY_SECONDS_IN_MILLIS));
            System.out.println("  Last 1m: " + stats.getStats(now, MINUTE_IN_MILLIS));
            System.out.println("------------------------");
        });

        cleanupOldData(now);
    }

    private void cleanupOldData(long currentTime) {
        long threshold = currentTime - MAX_RETENTION_TIME;

        timings.forEach((name, stats) -> {
            stats.cleanup(threshold);
        });

        // Remove empty TimingStats objects to prevent map from growing indefinitely
        timings.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }


    private static class TimingStats {
        private long startTime;
        private final List<TimingRecord> records = new ArrayList<>();

        public synchronized void start() {
            this.startTime = System.currentTimeMillis();
        }

        public synchronized void stop() {
            if (startTime > 0) {
                long duration = System.currentTimeMillis() - startTime;
                records.add(new TimingRecord(System.currentTimeMillis(), duration));
                startTime = 0;
            }
        }

        public synchronized void cleanup(long threshold) {
            records.removeIf(record -> record.timestamp < threshold);
        }

        public synchronized boolean isEmpty() {
            return records.isEmpty() && startTime == 0;
        }

        public StatsResult getStats(long currentTime, long period) {
            long threshold = currentTime - period;
            List<Long> recentDurations;
            synchronized (this) {
                recentDurations = new ArrayList<>(records).stream()
                        .filter(r -> r.timestamp >= threshold)
                        .map(r -> r.duration)
                        .toList();
            }

            if (recentDurations.isEmpty()) {
                return new StatsResult(0, 0, 0, 0);
            }

            long count = recentDurations.size();
            long total = recentDurations.stream().mapToLong(Long::longValue).sum();
            long avg = total / count;
            long max = recentDurations.stream().mapToLong(Long::longValue).max().orElse(0);
            long min = recentDurations.stream().mapToLong(Long::longValue).min().orElse(0);

            return new StatsResult(count, avg, min, max);
        }
    }

    private record TimingRecord(long timestamp, long duration) {}

    private record StatsResult(long count, long avg, long min, long max) {
        @Override
        public String toString() {
            return String.format("Count: %d, Avg: %dms, Min: %dms, Max: %dms", count, avg, min, max);
        }
    }
}
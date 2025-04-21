import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    executor: 'ramping-vus',
    startVUs: 0,
    stages: [
        { duration: '30s', target: 50 },  // Ramp-up to 10 VUs over 30 seconds
        { duration: '1m', target: 50 },     // Stay at 10 VUs for 1 minute
        { duration: '30s', target: 2 },     // Ramp-down to 0 VUs over 30 seconds
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'],  // 95% of requests should be below 500ms
    },
};

export default function () {
    let response = http.get('http://192.168.186.130:8080/grades');
    check(response, {
        'status is 200': (r) => r.status === 200,
    });

    sleep(1);
}
import axios from 'axios';

const BASE_API_URL = 'http://localhost:8080/api/v1/account';

export function signupApi(payload) {
	return axios.post(`${BASE_API_URL}/signup`, payload);
}

export function loginApi(payload) {
	return axios.post(`${BASE_API_URL}/login`, payload);
}

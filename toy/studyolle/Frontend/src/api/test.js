import axios from 'axios';

export function testApi() {
	return axios.get('https://jsonplaceholder.typicode.com/posts');
}

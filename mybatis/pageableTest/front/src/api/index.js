import axios from 'axios';

export const getList = page => {
	return axios.post('http://localhost:8090/api/list', page);
};

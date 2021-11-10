<template>
	<div class="container">
		<table class="table">
			<tr>
				<th>글번호</th>
				<th>글쓴이</th>
				<th>내용</th>
				<th>등록날짜</th>
			</tr>
			<tr v-for="item in data" :key="item.idx">
				<td>{{ item.idx }}</td>
				<td>{{ item.name }}</td>
				<td>{{ item.content }}</td>
				<td>{{ item.regDate }}</td>
			</tr>
		</table>
		<Pagination :pageable="pageable" @goPage="goPage" />
	</div>
</template>

<script>
import { getList } from '../api';

import Pagination from '@/components/Pagination.vue';

export default {
	components: {
		Pagination,
	},
	data() {
		return {
			pageable: {},
			data: [],
		};
	},
	created() {
		this.getData();
	},
	methods: {
		goPage(pageNumber) {
			this.pageable.pageNumber = pageNumber;
			this.getData();
		},
		async getData() {
			const { data } = await getList({
				pageNumber: this.pageable.pageNumber || 0,
				pageSize: 10,
			});
			this.data = data.content;
			this.pageable = data.pageable;
			this.pageable.totalElements = data.totalElements;
			this.pageable.totalPages = data.totalPages;
			this.pageable.first = data.first;
			this.pageable.last = data.last;
			console.log(this.pageable);
		},
	},
};
</script>

<style></style>

import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes: [
		{
			path: '/',
			name: 'Home',
			component: () => import(/* webpackChunkName: "about" */ '@/views/HomePage.vue'),
		},

		// 404
		{
			path: '/404',
			name: 'Not Found',
			component: () => import('@/views/NotFound.vue'),
		},
		{
			path: '*',
			redirect: '/404',
		},
	],
});

router.beforeEach((to, from, next) => {
	// if (to.meta.auth) {
	// 	// 토큰 유효성 검사
	// 	next('/login');
	// }

	next();
});

export default router;

<template>
	<div id="app">
		<router-view />
	</div>
</template>

<style lang="scss">
#app {
}
body {
	background-color: #f0f0f0 !important;
}
#nav {
	padding: 30px;

	a {
		font-weight: bold;
		color: #2c3e50;

		&.router-link-exact-active {
			color: #42b983;
		}
	}
}
</style>

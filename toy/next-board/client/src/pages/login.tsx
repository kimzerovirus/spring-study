import { Button, Container, Grid, TextField, Typography } from '@mui/material';
import React from 'react';

const Login = () => {
	// const handleSubmit = (e: any) => {
	// 	e.preventDefault();
	// 	const data = new FormData(e.target);
	// 	const email = data.get('email');
	// };

	const submitHandler = (e: React.SyntheticEvent) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			email: { value: string };
			password: { value: string };
		};
		console.log(target.email.value, target.password.value);
	};

	return (
		<Container component="main" maxWidth="xs" style={{ marginTop: '8%' }}>
			<Grid container spacing={2}>
				<Grid item xs={12}>
					<Typography component="h1" variant="h5">
						로그인
					</Typography>
				</Grid>
			</Grid>
			<form noValidate onSubmit={submitHandler}>
				{''}
				<Grid container spacing={2}>
					<Grid item xs={12}>
						<TextField
							variant="outlined"
							required
							fullWidth
							id="email"
							name="email"
							label="이메일 주소"
							autoComplete="email"
						/>
					</Grid>
					<Grid item xs={12}>
						<TextField
							variant="outlined"
							required
							fullWidth
							id="password"
							name="password"
							label="패스워드"
							type="password"
							autoComplete="current-password"
						/>
					</Grid>
					<Grid item xs={12}>
						<Button type="submit" fullWidth variant="contained" color="primary">
							로그인
						</Button>
					</Grid>
				</Grid>
			</form>
		</Container>
	);
};

export default Login;

import { Button, Container, Grid, TextField, Typography } from '@mui/material';
import axios from 'axios';
import Link from 'next/link';
import { useRouter } from 'next/router';
import React, { FormEvent, useState } from 'react';

import { useAuthState } from '../security/auth';

function Register() {
	const [email, setEmail] = useState('');
	const [username, setUsername] = useState('');
	const [password, setPassword] = useState('');
	const [errors, setErrors] = useState<any>({});
	const { authenticated } = useAuthState();

	const router = useRouter();
	if (authenticated) router.push('/');

	const handleSubmit = async (event: FormEvent) => {
		event.preventDefault();
		try {
			const res = await axios.post('/auth/register', {
				email,
				password,
				username,
			});
			console.log('res', res);
			router.push('/login');
		} catch (error: any) {
			console.log('error', error);
			setErrors(error.response.data || {});
		}
	};

	return (
		<Container component="main" maxWidth="xs" style={{ marginTop: '8%' }}>
			<Grid container spacing={2}>
				<Grid item xs={12}>
					<Typography component="h1" variant="h5">
						회원가입
					</Typography>
				</Grid>
			</Grid>
			<form noValidate onSubmit={handleSubmit}>
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
							id="username"
							name="username"
							label="이름"
							autoComplete="username"
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
							회원 가입
						</Button>
					</Grid>
				</Grid>
			</form>
			<Grid container sx={{ mt: 2 }}>
				<Grid item xs>
					이미 가입하셨나요?
					<Link href="/login">
						<a>로그인</a>
					</Link>
				</Grid>
			</Grid>
		</Container>
	);
}

export default Register;

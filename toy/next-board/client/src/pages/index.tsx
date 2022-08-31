import type { NextPage } from 'next';
import Head from 'next/head';
import Image from 'next/image';

import styles from '../styles/Home.module.css';

const Home: NextPage = () => {
	return (
		<div className={styles.container}>
			<main>
				<h1 className="text-3xl">Hello World!</h1>
			</main>
		</div>
	);
};

export default Home;

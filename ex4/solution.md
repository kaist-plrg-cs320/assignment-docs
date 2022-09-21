$\sigma,\Lambda\vdash n\Rightarrow n$

$\Large\frac{\sigma,\Lambda\vdash e_1\Rightarrow n_1\quad\sigma,\Lambda\vdash e_2\Rightarrow n_2}{\sigma,\Lambda\vdash e_1+e_2\Rightarrow n_1+n_2}$

$\Large\frac{\sigma,\Lambda\vdash e_1\Rightarrow n_1\quad\sigma[x\mapsto n_1],\Lambda\vdash e_2\Rightarrow n_2}{\sigma,\Lambda\vdash {\sf val}\ x=e_1;e_2\Rightarrow n_2}$

$\Large\frac{x\in\mathit{Domain}(\sigma)}{\sigma,\Lambda\vdash x\Rightarrow\sigma(x)}$

$\Large\frac{\sigma,\Lambda\vdash e\Rightarrow n\quad x\in\mathit{Domain}(\Lambda)\quad\Lambda(x)=({\sf def}\ x(x_1)=e_1)\quad[x_1\mapsto n],\Lambda\vdash e_1\Rightarrow n_1}{\sigma,\Lambda\vdash x(e)\Rightarrow n_1}$

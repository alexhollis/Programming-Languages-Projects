page(a).
page(b).
page(c).
page(d).
page(e).
page(f).
page(ma).
page(mb).
page(mc).
page(md).
page(me).
link(b,c).
link(c,b).
link(e,b).
link(d,a).
link(f,b).
link(d,b).
link(e,d).
link(e,f).
link(f,e).
link(ma,b).
link(ma,e).
link(mb,b).
link(mb,e).
link(mc,b).
link(mc,e).
link(md,e).
link(me,e).
baserank(A,B):- page(A),countpages(page(_),Num), B is 1/Num.
countpages(Pages,Num):-
	findall(1,Pages,L),
	length(L,Num).
go:-findall(X,page(X),L),
	foreach(member(X,L),baserank(X,Z)),
	foreach(member(X,L),assertz(rank_of(X,Z))),
	assertz(linker(ran,0)),
	foreach(member(X,L),updaterank2(X)).
updaterank2(A):-findall(Z,link(Z,A),L),
	retractall(linker(G,N)),
	foreach(member(Y,L),inlinker(Y,_)),
	findall(C,linker(P,C),K),
	sum_list(K,J),
	retract(rank_of(A,X)),
	assert(rank_of(A,J)).
outlinks(Y,G):-findall(Z,link(Y,Z),L),
	length(L,G).
inlinker(Y,U):-
	rank_of(Y,X),
	outlinks(Y,G),
	%U is X/G,
	assertz(linker(Y,(X/G))).
cleanup:-retractall(rank_of(X,Y)),
	retractall(linker(X,Y)).

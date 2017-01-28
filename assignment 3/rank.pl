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
	print(Z),
	foreach(member(X,L),assertz(rank_of(X,Z))),
	foreach(member(X,L),updaterank2(X)).
updaterank2(A):-findall(Z,link(Z,A),L),
	foreach(member(Y,L),(listmker(Y,I))),
	sum_list(I,V),
	retract(rank_of(A,_)),
	assert(rank_of(A,V)).
outlinks(Y,G):-findall(Z,link(Y,Z),L),
	length(L,G).
adder(Y,U):-
	rank_of(Y,X),
	outlinks(Y,G),
	U is X/G.
listmker(X,I):-adder(X,Y),
	add_list(Y,I,I).
add_list([], L, L).
add_list([H|T], L, L1) :- add(H, L2, L1), add_list(T, L, L2).

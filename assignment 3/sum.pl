c:- ['sum.pl'].

sum_of([], 0).

sum_of([H|T], Sum):-
	sum_of(T, Sum2),
	Sum is H + Sum2.
	
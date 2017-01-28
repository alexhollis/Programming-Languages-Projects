c:-['pagerank.pl'].


:- use_module(library(ugraphs)).

vertex(a).
vertex(b).
vertex(c).
vertex(d).
vertex(e).
vertex(f).



graph('[a-[],b-[c],c-[b],d-[a],e-[b,d,f],f-[b,e]]'). 




set_rank(V,R):-vertex(V),
  retractall(rank(V,_)),
  assertz(rank(V,R)).
get_rank(V,R):-vertex(V),rank(V,R).


init:-
set_rank(a,0.16),
set_rank(b,0.16),
set_rank(c,0.16),
set_rank(d,0.16),
set_rank(e,0.16),
set_rank(f,0.16).




get_vertices(Graph):- vertices(Graph,_Vertices).

get_edges(Graph):- edges(Graph, _Edges).


/*calc_rank(Vertex,Newrank):-

	new_rank = get_rank(Vertex) + sum_of(ranks of all children ( nodes pointing to it) ).
	
	rank_sum([], 0).
	rank_sum([H|T], Sum) :-
		rank_sum(T, Rest),
		Sum is H + Rest.
	

*/





update_rank(V,Rank):-
	
	rank_of(V,Graph),
	calc_rank(V,Newrank),
	retractall(rank_of(V,_)),
	assert(rank_of(V,Newrank)).
	
	
	
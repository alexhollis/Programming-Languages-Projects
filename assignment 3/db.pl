c:-['db.pl'].

/*

assert(Fact) or assert(Rule)
*/

v(1).
v(2).
v(3).

set_rank(V,R):-v(V),
  retractall(rank(V,_)),
  assertz(rank(V,R)).
get_rank(V,R):-v(V),rank(V,R).


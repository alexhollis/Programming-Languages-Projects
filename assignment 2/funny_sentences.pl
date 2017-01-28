% CSCE 4430 Assignment 2 - Prolog program to make funny sentences
% Designed By: Alex Hollis
% This prolog program will use the following facts to create a strucutured sentence in all possilbe combinations that it can resolve in 5 words.

%Facts

%articles
word(article,a).
word(article,every).
word(article,each).
word(article,at).

%nouns
word(noun,student).
word(noun,teacher).
word(noun,cop).
word(noun,professor).
word(noun,desk).
word(noun,books).
word(noun,door).

%verbs
word(verb,eats).
word(verb,likes).
word(verb,runs).
word(verb,farted).
word(verb,laughed).
  
%sentence structure will be 5 words, consiting as follows: article, noun, verb, article, and noun.  
sentence(Word1,Word2,Word3,Word4,Word5) :-
        word(article,Word1),
        word(noun,Word2),
        word(verb,Word3),
        word(article,Word4),
        word(noun,Word5).
		
%the creation function is used to print out the sentences this grammar generates, rather than just variable names.
%the false at the end is needed to have the program print all possible sentences, and for the write calls to work properly.
		
create_sentence(A,B,C,D,E) :-
		sentence(A,B,C,D,E),
		write(A),write(' '),
		write(B),write(' '),
		write(C),write(' '),
		write(D),write(' '),
		write(E),nl,false.
		
% end of program
		
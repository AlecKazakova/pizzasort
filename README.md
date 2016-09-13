### UPDATE 13/09/16 STILL NO SOLUTION SO IF U SOLVE THIS I WILL BUY YOU A PIZZA.

# pizzasort

You are Isafan Ofpizza, the most influential pizza critic in the entire world, and have
been tasked with ranking all the pizzas of New York. Your readers will take your ranking
as truth and be infinitely upset if you spread slanderous truths about the tastiness
of pizzas. However, being an expert of the business you know that the further down
a pizza is ranked, the less likely people are going to care that the ranking is wrong.
You know that the first pizza must absolutely (100% of the time) be the best pizza
in New York, but you only need to get the second best pizza right with 99% accuracy, the
third must be 98% accurate, all the way down to the 100th pizza which needs 0% accuracy.
The 100th pizza can be wrong and no one will care. The problem is pizza prices have
surged with the recent tomato apocalypse causing pizzas to cost 100$ each. Because you are
Isafan Ofpizza, you can immediately tell which is the better pizza of any two pizzas with
100% accuracy, but doing so will cost you 200$ total (100$ for each pizza). What is the
cheapest way you can rank all the pizzas of New York such that they are accurate enough
to convince your readers?

In other words:

Given a comparator that takes O(1) time to run, come up with a O(n) sorting algorithm
which gets the ith position correct (n - (i - 1))/n of the time given all permutations
of the input list. 

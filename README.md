# Gwen't

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of the
[_Gwent_](https://www.playgwent.com/en) card game developed by [_CD PROJEKT RED_](https://cdprojektred.com/en/)

---

If you want to be part of this adventure, let's go! First, do you know this game? I didn't know it before starting to
work on this clone, so it's fine, Gwent is a turn-based strategy card game, to play it you need two players who "fight"
against each other, each one having their own name, card deck and hand, and lastly, a gem counter, each player starts
with 3 gems, and lose one each time they lose a round.

There are two types of cards, Unit cards and Weather cards, and they are placed in a board that has one section for 
the Unit cards for each player and a shared section for the Weather cards. So it's clear that these two types of cards 
have different purposes, the most important things to know is that Unit cards have a number of strength and Weather
cards can affect that strength for every Unit card place in the board, so the winner of the round is the player that 
adds the most strength with their cards placed on the board. To win the game you need to win 2 out of 3 rounds.

Up until now, this implementation only allows instancing a player (with a name, a card deck, a card hand and a gem 
counter), setting your card deck (shuffling the cards) which needs to be done before each round, and drawing a card 
from your deck and adding it to your hand. But don't worry, in the near future we'll be adding more, stay tuned! 
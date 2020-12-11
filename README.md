# MC-Item-Shuffle
Minecraft minigame plugin where players have to find a random item in a set time limit

Overview: Each player is assigned a random item at the beginning of the game that they are tasked with finding. Once obtained, players must click with the item in their hand to
register it. Once all players get their item or the round time passes (default 8 minutes) players are assigned a new item. Players who don't get their item are eliminated. 
Items like bedrock and end items are not included in the game.

Commands:

  startItemShuffle: starts the minigame, default roudn length is 8 minutes. You can set a custom game length by putting the seconds after the command
  
  endItemShuffle: ends the minigame
  
  reroll: assigns a new item to the player who uses the command
  
  giveup: removes the player who uses the command from the game
  
  getCurrentItem: prints to consol your current assigned item

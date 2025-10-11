PlayerEvents.loggedIn(event => {
  const player = event.player;
  
  if (!player.persistentData.contains("startInv")) {
    player.give("patchouli:guide_book[patchouli:book=\"patchouli:everything_guide\"]");

    player.persistentData.putBoolean("startInv", true);
  }
});

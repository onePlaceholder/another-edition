ServerEvents.playerLoggedIn(event => {
  const player = event.player;
  
  if (!player.persistentData.contains("gotGuideBook")) {
    player.give(Item.of("patchouli:guide_book", `{patchouli:{"book":"patchouli:everything_guide"}}`));

    player.persistentData.putBoolean("gotGuideBook", true);
  }
});

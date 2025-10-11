ServerEvents.loaded(event => {
  function setGamerule(gamerule, value) {
    event.server.runCommand("gamerule " + gamerule + " " + value);
  }
  
  setGamerule("announceAdvancements", false);
});
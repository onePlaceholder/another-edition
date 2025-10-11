ServerEvents.loaded(event => {
  function setGamerule(gamerule, value) {
    event.server.runCommand("gamerule " + gamerule + " " + value);
  }
  
  console.log("Setting Gamerules")
  
  setGamerule("announceAdvancements", false);
});
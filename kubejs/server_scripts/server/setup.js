ServerEvents.loaded(event => {
  function runCommand(command) {
    event.server.runCommand(command);
  }
  function setGamerule(gamerule, value) {
    runCommand("gamerule " + gamerule + " " + value);
  }
  
  setGamerule("announceAdvancements", false);
  runCommand("scoreboard objectives add gm4_creative dummy");
  
  runCommand("scoreboard players set $cooldown gm4_creative 1");
  runCommand("scoreboard players set $global_cooldown gm4_creative 2147483647")
});
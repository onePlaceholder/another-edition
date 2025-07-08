StartupEvents.registry('block', event => {
  event
    .create('nether_catalyst')
    .displayName('Nether Catalyst')
    .glassSoundType()
    .hardness(3)
    .resistance(3)
    .requiresTool(true)
    .tagBlock('minecraft:mineable/pickaxe')
    .tagBlock('minecraft:needs_iron_tool')
    .defaultCutout()
    .lightLevel(0.5)
})
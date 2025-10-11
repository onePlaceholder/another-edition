PlayerEvents.advancement(event => {
    const modId = (event.getAdvancement().getId() + "").split(":")[0];
    if (modId == "lootr") {
        console.log(modId);
        event.cancel();
    }
});
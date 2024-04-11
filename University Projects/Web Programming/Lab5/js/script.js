$(function() {
    var draggedItemHeight;
    var wasOut = false;
    var pivot;

    $(".sortable-container").sortable({
        tolerance: "pointer",
        start: function(event, ui) {
            draggedItemHeight = ui.item.outerHeight();
            wasOut = false;
            pivot = ui.item;
        },
        out: function(event, ui) {
            if (ui.position.top < 0 || ui.position.left < 0 || ui.position.top > $(this).height() || ui.position.left > $(this).width()) {
                pivot.prevAll(".sortable-item").not(ui.item).animate({ top: "+=" + draggedItemHeight }, 300);
                wasOut = true; //
                console.log("OUT TRIGGERED");
            }
        },
        over: function(event, ui) {
            if (wasOut) {
                pivot.prevAll(".sortable-item").not(ui.item).animate({ top: "-=" + draggedItemHeight }, 300);
            }
        },
        change: function(event, ui) {
            pivot = ui.placeholder;  
        }
    });
});

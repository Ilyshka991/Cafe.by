(function ($) {
    $.fn.placeholder = function () {

        // Browser natively supports placeholders? Bail.
        if (typeof (document.createElement('input')).placeholder != 'undefined')
            return $(this);

        // No elements?
        if (this.length == 0)
            return $this;

        // Multiple elements?
        if (this.length > 1) {

            for (var i = 0; i < this.length; i++)
                $(this[i]).placeholder();

            return $this;

        }

        // Vars.
        var $this = $(this);

    };



})(jQuery);
if (process.env.NODE_ENV === "production") {
    const opt = require("./is-odd-or-even-week-slinky-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./is-odd-or-even-week-slinky-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./is-odd-or-even-week-slinky-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}

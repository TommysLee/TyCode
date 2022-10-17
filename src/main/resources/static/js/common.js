/*
 * 官方文档：https://kazupon.github.io/vue-i18n/zh/started.html
 */
// 创建 VueI18n 实例
const i18n = new VueI18n({
    locale: 'zh_CN', // 设置语言环境
    fallbackLocale: 'zh_CN', // 预设的语言环境【当前语言环境没有要获取的值时，默认从这个语言环境查找（预设的语言环境·首选语言缺少翻译时要使用的语言）】
    messages: {"zh_CN":{}},  // 设置各本地化的语言环境信息
    silentFallbackWarn: true, // 是否在回退到 fallbackLocale 或 root 时取消警告（如果为 true，则仅在根本没有可用的转换时生成警告，而不是在回退时。）
    silentTranslationWarn: true, //是否取消本地化失败时输出的警告
    preserveDirectiveContent: true // 解决翻译内容闪烁的问题(有过渡动画时，可复现此问题)
});

<!-- VUE混入：实现功能复用 -->
const mixins = [{
    i18n,
    vuetify: new Vuetify(),
    data: function() {
        return {
            fullscreenIcon: "mdi-fullscreen",
            locales: [{lang: "zh_CN", text: "简体中文"},{lang: "en_US", text: "English"},{lang: "ja_JP", text: "日本語"}],
            lang: "zh_CN",
            showToast: false,
            toastText: ''
        }
    },
    watch: {
        lang(val, oldVal) {
            $cookies.set("lang", val);
            this.$i18n.locale = val;
            console.log('lang: ' + val);
        }
    },
    created() {
        this.lang = $cookies.get("lang") || this.lang;
        this.fullScreenListener();
    },
    methods: {
        fullScreenListener() {
            screenfull.onchange(() => {
                this.fullscreenIcon = screenfull.isFullscreen? "mdi-fullscreen-exit" : "mdi-fullscreen";
            });
        },
        toggleFullScreen() {
            screenfull.toggle();
        },
        clearCache() {
            doAjax("cls", null, (response) => {
                this.toastText = this.$t("清除缓存成功");
                this.showToast = true;
            });
        },
        switchLang() {
        }
    }
}];

/**
 * 封装Axios Ajax
 */
function doAjax(url, params, callback, method) {
    method = method || "GET";
    method = method.toUpperCase();
    params = params || {};

    if (url) {
        let _params = "";
        let promise;
        switch(method) {
            case "POST":
                for (let p in params) {
                    let val = params[p];
                    if (undefined != val && null != val && '' != val) {
                        _params += (p + "=" + val + "&")
                    }
                }
                _params = _params.trim();
                params = _params.length > 0? _params.substr(0, _params.length - 1) : _params;

                promise = axios.post(url, params);
                break;
            default:
                params = {"params": params};
                promise = axios.get(url, params);
        }

        // Ajax回调
        promise.then((response) => {
            if (callback)
                callback(response);
        }).catch((err) => {
            console.log("Ajax请求异常：" + err);
        });
    }
}
<!-- VUE初始化 -->
let app = new Vue({
    el: "#app",
    mixins,
    data: {
        codeTabIndex: null,
        activeNodeCls: "ace-actived",
        isSupported: true,
        editors: []
    },
    watch: {
        codeTabIndex(val, oldVal) {
            console.log("codeTabIndex值变更：" + oldVal + " ------> " + val);

            // 隐藏当前的源代码视图
            let elNode = document.querySelector('.code-area.' + this.activeNodeCls);
            if (elNode) {
                elNode.classList.remove(this.activeNodeCls);
                elNode.style.display = "none";
            }

            // 显示新源代码视图
            elNode = document.querySelectorAll('.code-area')[val];
            elNode.style.display = "block";
            elNode.classList.add(this.activeNodeCls);
        }
    },
    created() { // 在Created阶段，初始化代码视图
        let _this = this;
        document.querySelectorAll('.code-area').forEach(function(editorEl) { // 排版页面上的源代码
            let theme = editorEl.dataset['aceTheme'] || "ace/theme/cobalt";
            let mode = editorEl.dataset['aceMode'] || "ace/mode/java";

            let editor = ace.edit(editorEl, {
                readOnly: true,
                highlightActiveLine: false,
                highlightGutterLine: false,
                showLineNumbers: true,
                showPrintMargin: false,
                theme: theme,
                mode: mode
            });
            editor.removeLines();

            _this.editors.push(editor);
        });

        // 执行浏览器检查
        if (!this.checkIE()) {
            // 剪切板功能初始化
            let clipboard = new Clipboard('#copyBtn', {
                text: function(trigger) {
                    return _this.editors[_this.codeTabIndex].getValue();
                }
            });
            clipboard.on('success', function(e) {
                e.clearSelection();
                _this.toastText = _this.$t("拷贝成功");
            });
            clipboard.on('error', function(e) {
                _this.toastText = _this.$t("拷贝失败");
            });
        }
    },
    methods: {
        checkIE() { // 检查是否为IE浏览器
            let regStr_ie = /msie [\d.]+/gi;
            let agent = navigator.userAgent.toLowerCase();
            let bv = agent.match(regStr_ie);
            if (-1 != agent.indexOf("msie")) {
                let versionNum = null != bv? bv.toString().replace(/\w*\s*([\d])+.*/, "$1") : 1;
                if (parseInt(versionNum) < 9) {
                    this.isSupported = false;
                }
            }
            return !this.isSupported;
        },
        copySource() { // 复制当前显示的源码
            console.log(this.editors[this.codeTabIndex].getValue());
            this.showToast = true;
            if (!this.isSupported) {
                this.toastText = this.$t('IE浏览器不支持复制功能')
            }
        },
        switchLang() {
            window.location.reload();
        }
    }
});

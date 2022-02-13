<!-- VUE初始化 -->
let app = new Vue({
    el: "#app",
    data: {
        datasources: [], // 后台获取的数据源
        templateGroups: [], // 后台获取的模板组数据
        valid: false, // 表单验证标识
        posting: false, // 数据提交标识
        configData: { // 表单对象数据
            datasource: "",
            templateGroup: "",
            tabName: "",
            pkgName: "",
            author: "TyCode"
        }
    },
    computed: {
        notEmptyRules() { // 规则：表单非空验证
            return [
                (v) => {
                    return v.trim().length > 1;
                }
            ];
        }
    },
    methods: {
        validData() { // 表单验证时调用的方法
            if(this.$refs.form.validate()) {
                console.log("提交中...");
                this.posting = true;

                console.log(JSON.stringify(this.configData));
                this.$refs.form.$el.submit();
            }
        },
        switchLang() {
            window.location.reload(true);
        }
    },
    created() { // 在Created阶段，从后台加载数据
        let _this = this;
        doAjax("datasources", null, (response) => {
            _this.datasources = response.data;
        });

        doAjax("templategroups", null, (response) => {
            _this.templateGroups = response.data;
        });
    }
});
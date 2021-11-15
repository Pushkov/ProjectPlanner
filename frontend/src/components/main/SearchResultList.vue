<template class=" mt-xl-5">
    <div class="w-75 m-auto">
        <div v-if="SEARCH_RESULT.documentDtoList !== undefined && SEARCH_RESULT.documentDtoList.length > 0">
            <div class=" row text-left my-3"
                 v-for="doc of SEARCH_RESULT.documentDtoList"
                 :key="doc.id"
            >
                Наименование документа: {{doc.name}}
            </div>
        </div>
        <div v-else-if="term.trim() !== ''">
            documents {{ ERROR_SEARCH_LOADING_MESSAGE }}
        </div>
        <div v-else>
        </div>
        <div v-if="SEARCH_RESULT.engineerDtoList !== undefined && SEARCH_RESULT.engineerDtoList.length > 0">
            <div class=" row text-left my-3"
                 v-for="eng of SEARCH_RESULT.engineerDtoList"
                 :key="eng.id"
            >
                Инженер: {{eng.lastName}} {{eng.firstName}}
            </div>

        </div>
        <div v-else-if="term.trim() !== ''">
            engineers {{ ERROR_SEARCH_LOADING_MESSAGE }}
        </div>
        <div v-else>
        </div>

    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: "SearchResultList",
        data() {
            return {
                resultList: {}
            }
        },
        props: {
            term: {
                type: String,
                default: ''
            },
            isDocument: {
                type: Boolean,
                default: false
            },
            isEngineer: {
                type: Boolean,
                default: false
            }
        },
        components: {},
        computed: {
            ...mapGetters([
                'SEARCH_RESULT',
                'ERROR_SEARCH_LOADING_MESSAGE'
            ]),
        },
        methods: {
            ...mapActions([
                'GET_SEARCH_RESULT'
            ]),
            refresh() {
                let par = {
                    "term": this.term,
                    "document": this.isDocument,
                    "engineer": this.isEngineer
                }
                this.GET_SEARCH_RESULT(par);
            }
        },
        watch: {
            term: function (val) {
                this.term = val;
                this.refresh();
            },
            isDocument: function (val) {
                this.isDocument = val;
                this.refresh();
            },
            isEngineer: function (val) {
                this.isEngineer = val;
                this.refresh();
            }
        },
        mounted() {
        }
    }
</script>

<style scoped>

</style>
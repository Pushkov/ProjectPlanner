<template class="mt-xl-5">
    <div class="  w-75 m-auto py-3">
        <input
                class="row w-75 bg-active border rounded-lg m-auto"
                v-model="term"
        >
        <div class="row w-75 bg-warn m-auto py-2">
            <div class="col-3"/>
            <MainCheckbox
                    class="col-sm"
                    :title="'Персонал'"
                    :isEdit="searchPersonal"
                    @isChecked="isSearchPersonal"
            />
            <MainCheckbox
                    class="col-sm"
                    :title="'Проекты'"
                    :isEdit="searchProject"
                    @isChecked="isSearchProject"
            />
            <MainCheckbox
                    class="col-sm"
                    :title="'Документы'"
                    :isEdit="searchDocument"
                    @isChecked="isSearchDocument"
            />
            <div class="col-3"/>
        </div>
        <div class="row ">
            <div class="col-sm">
                eng
                <div>{{searchPersonal}}</div>
            </div>
            <div class="col-sm">
                prj
                <div>{{searchProject}}</div>
            </div>
            <div class="col-sm">
                doc
                <div>{{searchDocument}}</div>
            </div>
            <div class="col-sm">
                term
                <div>{{term}}</div>
            </div>
            <div class="col-sm">
                test-term
                <div>{{test_term}}</div>
            </div>

        </div>
        <SearchResultList
                :term="term"
                :is-document="searchDocument"
                :is-engineer="searchPersonal"
        />
    </div>

</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import MainCheckbox from "@/components/MainCheckbox";
    import SearchResultList from "./main/SearchResultList";

    export default {
        name: "MainSearch",
        data() {
            return {
                searchPersonal: true,
                searchProject: false,
                searchDocument: true,
                term: '',
                test_term: ''
            }
        },
        components: {
            SearchResultList,
            MainCheckbox
        },
        computed: {
            ...mapGetters([])
        },
        methods: {
            ...mapActions([]),
            isSearchPersonal(value) {
                this.searchPersonal = value;
            },
            isSearchProject(value) {
                this.searchProject = value;
            },
            isSearchDocument(value) {
                this.searchDocument = value;
            },
        },
        watch: {
            term: function (val) {
                this.test_term = val;
            }
        },
        mounted() {
        }
    }
</script>

<style scoped>

</style>
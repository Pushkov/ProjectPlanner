<template class="mt-xl-5">
    <div class="  w-75 m-auto py-3">
        <h5>{{ $t("message.search.search_field") }}:</h5>
        <input
                class="row w-75 bg-active border rounded-lg m-auto"
                v-model="term"
        >
        <div class="row w-75 bg-warn m-auto py-2">
            <div class="col-3"/>
            <MainCheckbox
                    class="col-sm"
                    :title="$t('message.navigation.personal')"
                    :isEdit="searchPersonal"
                    @isChecked="isSearchPersonal"
            />
            <MainCheckbox
                    class="col-sm"
                    :title="$t('message.navigation.projects')"
                    :isEdit="searchProject"
                    @isChecked="isSearchProject"
            />
            <MainCheckbox
                    class="col-sm"
                    :title="$t('message.navigation.documents')"
                    :isEdit="searchDocument"
                    @isChecked="isSearchDocument"
            />
            <div class="col-3"/>
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
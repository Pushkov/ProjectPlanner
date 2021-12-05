<template>
    <div>
        <NavigationBar/>
        <WorkshopsList
                v-if="this.$route.fullPath === '/planner/workshops'"
        />
        <WorkshopsSingle
                v-else-if="hasPathWithSingleParam"
        />
        <div v-else>
            OTHER
        </div>
    </div>
</template>

<script>
    import NavigationBar from "@/components/nav/NavigationBar";
    import WorkshopsList from "../components/workshop/WorkshopsList";
    import WorkshopsSingle from "../components/workshop/WorkshopsSingle";

    export default {
        name: "Workshop",
        components: {
            WorkshopsSingle,
            WorkshopsList,
            NavigationBar
        },
        computed: {
            hasPathWithSingleParam() {
                let path = this.$route.fullPath;
                let par = this.$route.params.id;
                return path.endsWith(par);
            }
        },
        methods: {},
        mounted() {
        }
    }
</script>

<style scoped>

</style>
<template>
    <div>
        <NavigationBar/>
        <OverviewList/>
    </div>
</template>

<script>
    import NavigationBar from "@/components/nav/NavigationBar";
    import OverviewList from "../components/overview/OverviewList";

    export default {
        name: "Overview",
        components: {
            OverviewList,
            NavigationBar
        }
    }
</script>

<style scoped>

</style>
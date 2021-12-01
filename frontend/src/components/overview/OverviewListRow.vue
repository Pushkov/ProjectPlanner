<template>
    <tr>
        <td>{{ item.id }}</td>
        <td>{{ item.year }}</td>
        <td>{{ item.month }}</td>
        <td>{{ item.projectName }}</td>
        <td>{{ item.projectDesignation }}</td>
        <td>{{ item.departmentName }}</td>
        <td>{{ item.taskName }}</td>
    </tr>
</template>

<script>
    export default {
        name: "OverviewListRow",
        props: {
            item: {
                type: Object,
                default: () => {
                    return {}
                }
            }
        },
    }
</script>

<style scoped>

</style>
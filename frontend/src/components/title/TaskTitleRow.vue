<template>
    <tr>
        <td>
            {{task.number}}
        </td>
        <td>
            {{task.name}}
        </td>
    </tr>
</template>

<script>

    export default {
        name: "TaskTitleRow",
        props: {
            task: {
                type: Object,
                default: () => {
                    return {}
                }
            },
        },
        components: {}
    }
</script>

<style scoped>

</style>
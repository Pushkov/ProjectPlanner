<template>
    <div class="row p-3">
        <div class="col-sm">
            <div class="row">
                <h5 class="mx-3">Year:</h5>
            </div>
            <b-form-select
                    v-model="i1"
                    :options="[1,2]"
                    class="row text-secondary border rounded-lg"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
            ></b-form-select>
        </div>
        <div class="col-sm">
            <div class="row">
                <h5 class="mx-3">Month:</h5>
            </div>
            <b-form-select
                    v-model="currentMonth.value"
                    :options="months"
                    class="row text-secondary border rounded-lg"
                    value-field="value"
                    text-field="name"
                    disabled-field="notEnabled"
            ></b-form-select>
        </div>
        <div class="col-sm">
            <div class="row">
                <h5 class="mx-3">Department:</h5>
            </div>
            <b-form-select
                    v-model="i1"
                    :options="[1,2]"
                    class="row text-secondary border rounded-lg"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
            ></b-form-select>
        </div>


    </div>
</template>

<script>
    export default {
        name: "OverviewListSelectors",
        data() {
            return {
                currentMonth: {
                    value: -1,
                    name: 't name'
                },
                i1: {
                    id: 1,
                    name: 't name'
                },
                i3: {
                    id: 1,
                    name: 't name'
                },
            }
        },
        props: {
            months: {
                type: Array,
                default:
                    () => {
                        return []
                    }
            }
        }
    }

</script>

<style scoped>

</style>
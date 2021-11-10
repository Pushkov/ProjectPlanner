<template>
    <div class="basic-popup-body container-fluid">
        <div class="row my-1 mx-2" v-if="dataItem.id !== undefined">
            <div class="col-4 mr-2 ">Id:</div>
            <b-input
                    class="col-sm text-secondary bg-light border rounded-lg"
                    readonly
                    :value="dataItem.id"
                    v-model="dataItem.id"
            />
        </div>

        <div
                class="row my-1 mx-2"
                v-for="key of Object.keys(dataItem)"
                :key="key"

        >
            <div class="col-4 mr-2 ">{{key}}</div>
            <ul
                    class="col-sm text-secondary bg-light border rounded-lg"
                    readonly
                    v-if="Array.isArray(dataItem[key])"

            >
                <li v-for="value of dataItem[key]"
                    :role="value"
                    :key="value"
                >
                    {{value.name}}
                </li>
            </ul>
            <b-input
                    class="col-sm text-secondary bg-light border rounded-lg"
                    readonly
                    v-else
                    :value=dataItem[key]
                    v-model="dataItem[key]"
            />
        </div>


    </div>
    <!--    <template>-->
    <!--      <tr v-if="Object.keys(dataItem).length > 0" @click="itemInModal">-->
    <!--        <td v-for="key of Object.keys(role)"-->
    <!--            :role="key"-->
    <!--            :key="key"-->
    <!--        >-->
    <!--          <ul v-if="Array.isArray(dataItem[key])">-->
    <!--            <li v-for="value of dataItem[key]"-->
    <!--                :role="value"-->
    <!--                :key="value"-->
    <!--            >-->
    <!--              {{value.name}}-->
    <!--            </li>-->
    <!--          </ul>-->
    <!--          <template v-else>-->
    <!--            {{dataItem[key]}}-->
    <!--          </template>-->
    <!--        </td>-->
    <!--      </tr>-->
    <!--      <tr v-else>-->
    <!--        <td>object is empty</td>-->
    <!--      </tr>-->
    <!--    </template>-->


    <!--    <div class="row my-1 mx-2">-->
    <!--      <div class="col-4 mr-2 ">Учетная запись:</div>-->
    <!--      <b-input-->
    <!--          class="col-sm text-secondary  border rounded-lg"-->
    <!--          :readonly="!isEdit"-->
    <!--          :value="dataItem.login"-->
    <!--          v-model="dataItem.login"/>-->
    <!--    </div>-->
    <!--    <div class="row my-1 mx-2">-->
    <!--      <div class="col-4 mr-2 ">Имя:</div>-->
    <!--      <b-input-->
    <!--          class="col-sm text-secondary  border rounded-lg"-->
    <!--          :readonly="!isEdit"-->
    <!--          :value="dataItem.firstName"-->
    <!--          v-model="dataItem.firstName"/>-->
    <!--    </div>-->
    <!--    <div class="row my-1 mx-2">-->
    <!--      <div class="col-4 mr-2">Фамилия:</div>-->
    <!--      <b-input-->
    <!--          class="col-sm text-secondary border rounded-lg"-->
    <!--          :readonly="!isEdit"-->
    <!--          :value="dataItem.lastName"-->
    <!--          v-model="dataItem.lastName"/>-->
    <!--    </div>-->
    <!--    <div class="row my-1 mx-2">-->
    <!--      <div class="col-4 mr-2">Должность:</div>-->
    <!--      <b-input-->
    <!--          class="col-sm text-secondary  border rounded-lg"-->
    <!--          :readonly="!isEdit"-->
    <!--          :value="(item.position !== undefined) ? item.position : 'пусто'"-->
    <!--          v-model="dataItem.position"/>-->
    <!--    </div>-->
    <!--  </div>-->
</template>

<script>
    export default {
        name: "BasicModalView",
        data() {
            return {
                dataItem: {...this.item}
            }
        },
        props: {
            item: {
                type: Object,
                default: () => {
                    return {}
                }
            },
            isEdit: {
                type: Boolean,
                default: false
            }
        },
        computed: {
            getText(key) {
                return key.forEach(x => console.log(x));
                // return '123123';

            }

        },
        methods: {
            returnItem() {
                this.$emit('returnItem', this.dataItem);
            }
        }

    }
</script>

<style scoped>

</style>
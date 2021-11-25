<template>
  <div class="basic-popup-body container-fluid">

    <div class="row my-1 mx-2 bg- border rounded-lg">
      <p class="m-auto"> {{ key }}
      </p>
    </div>
    <div class="row my-1 mx-2"
         v-for="(value1,key1) of value"
         :key="key1"
    >
      <div class="col-4 mr-2 ">{{ key1 }}</div>
      <input
          class="col-sm text-secondary  border rounded-lg"
              :readonly="!isEdit"
              :value="value1"
          />
        </div>


    <!--      <template v-else>-->
    <!--        <div class="row my-1 mx-2">-->
    <!--          <div class="col-4 mr-2 ">{{ key }}</div>-->
    <!--          <input class="col-sm text-secondary  border rounded-lg"-->
    <!--                 :readonly="!isEdit"-->
    <!--                 :value="value"-->
    <!--          />-->
    <!--        </div>-->
    <!--      </template>-->
  </div>
</template>

<script>
export default {
  name: "RolePermissionModalView",
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
  computed: {},
  methods: {
    returnItem() {
      this.$emit('returnItem', this.dataItem);
    }
  }

}
</script>

<style scoped>

</style>
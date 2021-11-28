<template>
  <div class="basic-popup-body container-fluid">

    <div class="row my-1 mx-2">
      <div class="col-4 mr-2 ">Имя:</div>
      <b-input
          class="col-sm text-secondary  border rounded-lg"
          :readonly="!isEdit"
          :class="getErrorName"
          :value="dataItem.name"
          v-model="dataItem.name"/>
    </div>
    <div class="row my-1 mx-2">
      <h6 class="m-auto text-xl" v-if="error.name !== undefined"><small class="text-danger"> {{ error.name }}</small>
      </h6>
      <h6 class="m-auto text-xl" v-else><small>&nbsp; </small></h6>
    </div>
    <div class="row my-1 mx-2">
      <div class="col-4 mr-2 ">Описание:</div>
      <b-input
          class="col-sm text-secondary  border rounded-lg"
          :readonly="!isEdit"
          :value="dataItem.comment"
          v-model="dataItem.comment"/>
    </div>
    <div class="row my-1 mx-2">
      <h6 class="m-auto text-xl"><small>&nbsp; </small></h6>
    </div>
  </div>

</template>

<script>
export default {
  name: "PermissionModalView",
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
    },
    error: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  computed: {
    getErrorName() {
      return this.error.name !== undefined ? 'border border-danger' : '';
    },

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
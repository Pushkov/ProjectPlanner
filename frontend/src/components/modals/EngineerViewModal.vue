<template>
  <div class="nic-popup-body container-fluid">
    <div class="row my-1 mx-2" v-if="itemUser.id !== undefined">
      <div class="col-4 mr-2 ">Id:</div>
      <b-input
          class="col-sm text-secondary bg-light border rounded-lg"
          readonly
          :value="itemUser.id"
          v-model="itemUser.id"
      />
    </div>
    <div class="row my-1 mx-2">
      <div class="col-4 mr-2 ">Учетная запись:</div>
      <b-input
          class="col-sm text-secondary  border rounded-lg"
          :readonly="!isEdit"
          :value="itemUser.login"
          v-model="itemUser.login"/>
    </div>
    <div class="row my-1 mx-2">
      <div class="col-4 mr-2 ">Имя:</div>
      <b-input
          class="col-sm text-secondary  border rounded-lg"
          :readonly="!isEdit"
          :value="itemUser.firstName"
          v-model="itemUser.firstName"/>
    </div>
    <div class="row my-1 mx-2">
      <div class="col-4 mr-2">Фамилия:</div>
      <b-input
          class="col-sm text-secondary border rounded-lg"
          :readonly="!isEdit"
          :value="itemUser.lastName"
          v-model="itemUser.lastName"/>
    </div>
    <div class="row my-1 mx-2">
      <div class="col-4 mr-2">Должность:</div>
      <b-input
          class="col-sm text-secondary  border rounded-lg"
          :readonly="!isEdit"
          :value="(item.position !== undefined) ? item.position : 'пусто'"
          v-model="itemUser.position"/>
    </div>
  </div>
</template>

<script>
export default {
  name: "EngineerViewModal",
  data() {
    return {
      itemUser: {...this.item}
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
  methods: {
    returnUser() {
      this.$emit('returnUser', this.itemUser);
    }
  }

}
</script>

<style scoped>

</style>
<!-- <template>
  
</template>
<script>
export default {
  name: 'Message'
  
}
</script> -->
<script>
import TextHandler from '@/components/TextHandler.vue'

export default {
  name: 'Message',
  components:{
    TextHandler
  },
  data: () => ({
    input: ''
  }),
  methods: {
    
  }
}
</script>

<template>
  <TextHandler/>
</template>

<style>

</style>
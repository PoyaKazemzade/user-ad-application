<script setup lang="ts">

</script>

<!-- MainLayout.vue -->
<template>
  <!-- Main Layout -->
  <BNavbar v-b-color-mode="'dark'" toggleable="lg" variant="secondary-subtle">
    <BNavbarBrand href="/home">Bazam</BNavbarBrand>
    <BNavbarToggle target="nav-collapse"/>
    <BCollapse id="nav-collapse" is-nav>
      <BNavbarNav>
        <BNavItem href="#">Buy</BNavItem>
        <BNavItem href="#">Sell</BNavItem>
      </BNavbarNav>
      <!-- Right aligned nav items -->
      <BNavbarNav class="ms-auto mb-2 mb-lg-0">
        <BNavItemDropdown right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <em>User</em>
          </template>
          <BDropdownItem href="#">Profile</BDropdownItem>
          <BDropdownItem href="#">Sign Out</BDropdownItem>
        </BNavItemDropdown>
      </BNavbarNav>
      <BNavForm class="d-flex">
        <BFormInput class="me-2" placeholder="Search"/>
        <BButton type="submit" variant="outline-success">Search</BButton>
      </BNavForm>
    </BCollapse>
  </BNavbar>

  <!-- Main Content -->
  <main>
    <!-- Dynamically rendered content -->
    <RouterView/>
  </main>

  <footer class="foooter">
    <span>Github repo <a href="https://github.com/PoyaKazemzade/user-ad-application">(www.github.com)</a></span></footer>
</template>

<style scoped>
.foooter {
  font-size: 0.8em;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0; 
  text-align: center;
  padding: 10px;
}

.foooter a {
  color: darkolivegreen; /* Style the link */
  text-decoration: none; /* Optional: remove the underline */
}
</style>
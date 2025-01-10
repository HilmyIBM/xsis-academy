function pesan() {
  if (confirm("Are you sure you want to order this services?") == true) {
    alert("Thank you for your order!");
  } else {
    alert("Kami tunggu pesanannya!");
  }
}

const showContainer = () => {
  let component = document.getElementById("services");
  if (component.style.display === "none") {
    component.style.display = "";
  } else {
    component.style.display = "none";
  }
};

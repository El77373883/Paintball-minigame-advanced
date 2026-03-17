private ItemStack[] inventory;

public void saveInventory(Player p){

    inventory = p.getInventory().getContents();

}

public void restoreInventory(Player p){

    p.getInventory().setContents(inventory);

}

if(e.getEntity() instanceof Player){

    Player victim = (Player) e.getEntity();

    if(e.getDamager() instanceof Snowball){

        Snowball ball = (Snowball) e.getDamager();

        if(ball.getShooter() instanceof Player){

            Player killer = (Player) ball.getShooter();

            victim.getWorld().strikeLightningEffect(victim.getLocation());

            CoinsManager.addCoins(killer,3);

            killer.sendMessage("§6+3 coins");

        }

    }

}

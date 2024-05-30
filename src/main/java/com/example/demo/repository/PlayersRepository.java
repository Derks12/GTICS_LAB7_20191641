package com.example.demo.repository;

import com.example.demo.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM lab7_player.players  WHERE mmr>6500 Order by mmr DESC limit 10;")
    List<Players> listarMmr();
}

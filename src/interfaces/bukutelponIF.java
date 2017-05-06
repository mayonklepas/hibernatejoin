/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Bukutelpon;
import java.util.List;

/**
 *
 * @author Minami
 */
public interface bukutelponIF {

    public void simpan(Bukutelpon b);

    public void update(Bukutelpon b);

    public void hapus(Bukutelpon b);

    public List<Bukutelpon> baca();

    public List<Bukutelpon> cari(String key);

}

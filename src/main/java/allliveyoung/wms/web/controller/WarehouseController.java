package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.Warehouse;
import allliveyoung.wms.service.WarehouseService;
import allliveyoung.wms.web.dto.WarehouseSaveDto;
import allliveyoung.wms.web.dto.WarehouseSearch;
import allliveyoung.wms.web.dto.WarehouseUpdateDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("warehouses")
public class WarehouseController {

  private final WarehouseService warehouseService;

  @GetMapping("save")
  public String saveWarehouseForm(Model model){
    model.addAttribute("warehouse" , new WarehouseSaveDto());
    return "warehouse/saveForm";
  }

  @PostMapping("save")
  public String saveWarehouseForm(@Validated @ModelAttribute("warehouse") WarehouseSaveDto warehouseSaveDto , BindingResult bindingResult ,
      RedirectAttributes redirectAttributes , @Login Member member){
    if (bindingResult.hasErrors()){
      return "warehouse/saveForm";
    }
    Long saveWarehouseId = warehouseService.saveWarehouse(warehouseSaveDto);
    redirectAttributes.addAttribute("id", saveWarehouseId);
    redirectAttributes.addAttribute("saveStatus", true);
    return "redirect:/warehouses/{id}";
  }


  @GetMapping("{id}/update")
  public String updateWarehouseForm(@PathVariable("id")Long id ,  Model model){
    Warehouse warehouse = warehouseService.findWarehouse(id);
    WarehouseUpdateDto warehouseUpdateDto = new WarehouseUpdateDto(warehouse.getName() , warehouse.getCode() ,
        warehouse.getAddress().getRoadNameAddress() , warehouse.getAddress().getJibunAddress() ,warehouse.getAddress().getDetailsAddress(),
        warehouse.getAddress().getZipcode());
    model.addAttribute("warehouse", warehouseUpdateDto);
    model.addAttribute("id", warehouse.getId());
    return "warehouse/updateForm";
  }

  @PostMapping("{id}/update")
  public String updateWarehouseForm(@Validated @ModelAttribute("warehouse")WarehouseUpdateDto warehouseUpdateDto ,@PathVariable("id") Long id ,
      BindingResult bindingResult , RedirectAttributes redirectAttributes , @Login Member member){
    if (bindingResult.hasErrors()){
      return "warehouse/updateForm";
    }
    warehouseService.updateWarehouse(id , warehouseUpdateDto);
    redirectAttributes.addAttribute("id", id);
    redirectAttributes.addAttribute("updateStatus", true);
    return "redirect:/warehouses/{id}";
  }



  @GetMapping
  public String getWarehouses(@ModelAttribute("warehouseSearch") WarehouseSearch warehouseSearch, Model model) {
    List<Warehouse> warehouses = warehouseService.findWarehouses(warehouseSearch);
    model.addAttribute("warehouses" , warehouses);
    model.addAttribute("warehouseSearch", warehouseSearch);
    return "warehouse/warehouses";
  }

  @GetMapping("{id}")
  public String getWarehouse(@PathVariable("id") Long id, Model model) {
    Warehouse warehouse = warehouseService.findWarehouse(id);
    model.addAttribute("warehouse", warehouse);
    return "warehouse/warehouse";
  }




}

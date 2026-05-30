package api.reports;

import api.utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath =
                    System.getProperty("user.dir")
                            + "/test-output/API_Report.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName("AutomationExercise API Report");
            spark.config().setDocumentTitle("API Automation Report");

            spark.config().setCss(
                    ".card-panel{border-radius:10px!important;}" +
                            ".chip{font-size:10px!important;}" +
                            "canvas{max-width:100%!important;}"
            );

            spark.config().setJs(

                    // ── Load Chart.js then run everything ──
                    "var s=document.createElement('script');" +
                            "s.src='https://cdn.jsdelivr.net/npm/chart.js';" +
                            "s.onload=function(){ setTimeout(customizeReport, 800); };" +
                            "document.head.appendChild(s);" +

                            "function customizeReport(){" +

                            // ── 1. Tests donut — draw on ExtentReports' existing canvas ──
                            "  var existingCanvas = document.getElementById('parent-analysis');" +
                            "  if(existingCanvas){" +
                            "    existingCanvas.width  = 200;" +
                            "    existingCanvas.height = 200;" +
                            "    existingCanvas.style.cssText='display:block;margin:10px auto';" +
                            "    new Chart(existingCanvas,{" +
                            "      type:'doughnut'," +
                            "      data:{" +
                            "        labels:['Passed','Failed','Skipped']," +
                            "        datasets:[{" +
                            "          data:[" +
                            "            statusGroup.passParent||0," +
                            "            statusGroup.failParent||0," +
                            "            statusGroup.skipParent||0" +
                            "          ]," +
                            "          backgroundColor:['#00c853','#ff1744','#ffd600']," +
                            "          borderWidth:2" +
                            "        }]" +
                            "      }," +
                            "      options:{" +
                            "        responsive:false," +
                            "        plugins:{" +
                            "          legend:{position:'bottom',labels:{font:{size:11}}}" +
                            "        }" +
                            "      }" +
                            "    });" +
                            "  }" +

                            // ── 2. Rename Device → Severity (runs 3 times to catch late renders) ──
                            "  function renameLabels(){" +
                            "    document.querySelectorAll('*').forEach(function(el){" +
                            "      if(el.childElementCount===0 && el.innerText){" +
                            "        var t=el.innerText.trim();" +
                            "        if(t==='Device') el.innerText='Severity';" +
                            "        if(t==='Author') el.innerText='Testers';" +
                            "      }" +
                            "    });" +
                            "  }" +
                            "  renameLabels();" +
                            "  setTimeout(renameLabels,1200);" +
                            "  setTimeout(renameLabels,2500);" +

                            // ── 3. Reorder summary cards: Tags → Severity → Author → System ──
                            "  setTimeout(function(){" +

                            "    var cols={};" +
                            "    document.querySelectorAll('.card-header').forEach(function(h){" +
                            "      var title=h.innerText.trim();" +
                            "      var col=h.closest('.col-lg-6,.col-md-12,.col-lg-4,.col-sm-12');" +
                            "      if(!col) col=h.closest('[class*=container]');" +
                            "      if(!col) return;" +
                            "      if(title==='Author'||title==='Testers')    cols.author   = col;" +
                            "      if(title==='Tags')                         cols.tags     = col;" +
                            "      if(title==='Severity'||title==='Device')   cols.severity = col;" +
                            "      if(title==='System/Environment')           cols.system   = col;" +
                            "    });" +

                            // Fallback — direct class selectors
                            "    if(!cols.author)   cols.author   = document.querySelector('.author-container');" +
                            "    if(!cols.tags)     cols.tags     = document.querySelector('.category-container');" +
                            "    if(!cols.severity) cols.severity = document.querySelector('.device-container');" +
                            "    if(!cols.system)   cols.system   = document.querySelector('.environment-container');" +

                            "    var anchor = cols.tags || cols.author;" +
                            "    if(!anchor) return;" +
                            "    var row = anchor.parentNode;" +
                            "    if(!row) return;" +

                            // Re-append in order: Tags → Severity → Author → System/Environment
                            "    if(cols.tags)     row.appendChild(cols.tags);" +
                            "    if(cols.severity) row.appendChild(cols.severity);" +
                            "    if(cols.author)   row.appendChild(cols.author);" +
                            "    if(cols.system)   row.appendChild(cols.system);" +

                            "  }, 1500);" +

                            "}" // end customizeReport
            );

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project",     "AutomationExercise API Framework");
            extent.setSystemInfo("Environment", ConfigReader.get("env"));
            extent.setSystemInfo("Base URL",    "https://automationexercise.com/api");
            extent.setSystemInfo("Author",      "Premdevi Kumawat");
            extent.setSystemInfo("Java",        System.getProperty("java.version"));
            extent.setSystemInfo("OS",          System.getProperty("os.name"));
        }

        return extent;
    }
}
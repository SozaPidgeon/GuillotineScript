import matplotlib.pyplot as plt


class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.x = 0
        self.y = 0
        
class Bin:
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.free_rects = []
        self.free_rects.append(Rectangle(width, height))
        
    def insert(self, rect):
        best_score = float('inf')
        best_rect = None
        best_index = None
        
        for i, free_rect in enumerate(self.free_rects):
            if rect.width <= free_rect.width and rect.height <= free_rect.height:
                score = free_rect.width * free_rect.height - rect.width * rect.height
                if score < best_score:
                    best_score = score
                    best_rect = free_rect
                    best_index = i
                    
        if best_rect is None:
            return False
        
        rect.x = best_rect.x
        rect.y = best_rect.y
        self.free_rects.remove(best_rect)
        
        if rect.width < best_rect.width:
            self.free_rects.append(Rectangle(best_rect.width - rect.width, rect.height))
            self.free_rects[-1].x = rect.x + rect.width
            self.free_rects[-1].y = rect.y
            
        if rect.height < best_rect.height:
            self.free_rects.append(Rectangle(best_rect.width, best_rect.height - rect.height))
            self.free_rects[-1].x = rect.x
            self.free_rects[-1].y = rect.y + rect.height
            
        return True
        
def guillotine_cut(width, height, rects, bin):
    rects = sorted(rects, key=lambda x: max(x.width, x.height), reverse=True)
    bin = Bin(width, height)
    for rect in rects:
        if not bin.insert(rect):
            return False
    return True

def orderTaker(items):
    stop = False
    while not stop:
        newMetal = input("metal size: ")
        try:
            if newMetal == "":
                stop = True
            else:
                items.append(newMetal)
        except:
            print("invalid size")

def plot_rectangles(rectangles, bin_width, bin_height):
    fig, ax = plt.subplots()
    ax.set_xlim([0, bin_width])
    ax.set_ylim([0, bin_height])
    for rectangle in rectangles:
        ax.add_patch(plt.Rectangle((rectangle.x, rectangle.y), rectangle.width, rectangle.height, fill=True))
    plt.show()

bin = Bin(97, 49)
items = []
metalSheets = []
orderTaker(items)
if len(items) == 0:
    print("no metal")
else:
    for metal in items:
        metalSplit = metal.split('x')
        width = float(metalSplit[0])
        height = float(metalSplit[1])
        metalSheets.append(Rectangle(width, height))

print(guillotine_cut(bin.width, bin.height, metalSheets, bin))

plot_rectangles(metalSheets, bin.width, bin.height)
